package com.opwar.opwar.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opwar.opwar.activities.MainActivity;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by URZU on 09/05/2016.
 */
public class ListPDF {
    /**
     * Crea un fichero PDF con el contenido de la lista del ejécito
     *
     * @param fileName
     * @param listaEjercito a imprimir
     *
     * @return nombre del fichero
     */
    public static String createPDF(String fileName, ListaEjercito listaEjercito)
            throws FileNotFoundException, DocumentException {
        File directory = createDirectoryIfNotExists();
        createFile(fileName, directory, listaEjercito);

        return fileName + ".pdf";
    }

    /**
     * Comprueba si existe un fichero PDF
     *
     * @param fileName del fichero
     * @return true si existe, false si no
     */
    public static boolean existsPDF(String fileName) {
        File directory = createDirectoryIfNotExists();
        File file = new File(directory, fileName + ".pdf");

        return file.exists();
    }

    /**
     * Borra un fichero PDF
     *
     * @param fileName del fichero
     * @return true si se ha borrado, false si no
     */
    public static boolean deletePDF(String fileName) {
        File directory = createDirectoryIfNotExists();
        File file = new File(directory, fileName + ".pdf");

        return file.delete();
    }


    /**
     * Crea el fichero PDF
     *
     * @param fileName
     * @param directory
     */
    private static void createFile(String fileName, File directory, ListaEjercito listaEjercito)
            throws FileNotFoundException, DocumentException {
        Document document = new Document();
        File file = new File(directory, fileName + ".pdf");
        FileOutputStream fOut = new FileOutputStream(file);

        PdfWriter.getInstance(document, fOut);

        //open the document
        document.open();
        CharSequence fecha = DateFormat.format(" kk:mm - dd/MM/yyyy", listaEjercito
                .getFechaCreacion());

        Paragraph p = new Paragraph();
        Font font = new Font();
        font.setSize(16);
        font.setStyle(Font.BOLD);
        p.setFont(font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.add(listaEjercito.getNombre() + " [" + listaEjercito.getPuntos() + " pts]");
        p.setSpacingAfter(36);
        document.add(p);

        p = new Paragraph("Nombre de la lista: " + fileName);
        document.add(p);

        p = new Paragraph();
        p.add("Fecha de creación: " + fecha.toString());
        p.setSpacingAfter(36);
        document.add(p);

        List<Regimiento> regimientos = listaEjercito.getRegimientos();
        List<Regimiento> comandantes = new ArrayList<>();
        List<Regimiento> unidadBasicas = new ArrayList<>();
        List<Regimiento> unidadEspeciales = new ArrayList<>();
        List<Regimiento> unidadSingulares = new ArrayList<>();

        for (Regimiento regimiento : regimientos) {
            if (regimiento.getUnidad() instanceof Comandante) {
                comandantes.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadBasica) {
                unidadBasicas.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadEspecial) {
                unidadEspeciales.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadSingular) {
                unidadSingulares.add(regimiento);
            }
        }

        listarRegimientos("COMANDANTES:", document, comandantes);
        listarRegimientos("UNIDADES BÁSICAS:", document, unidadBasicas);
        listarRegimientos("UNIDADES ESPECIALES:", document, unidadEspeciales);
        listarRegimientos("UNIDADES SINGULARES:", document, unidadSingulares);

        document.close();
    }

    private static void listarRegimientos(String nombreUnidad, Document document, List<Regimiento>
            regimientos) throws DocumentException {
        if (regimientos.size() != 0) {
            Paragraph p = new Paragraph(regimientos.size() + " x " + nombreUnidad);
            document.add(p);
            String text;
            com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
            list.setIndentationLeft(36);
            for (Regimiento regimiento : regimientos) {
                text = regimiento.getTamanyo() + " x " + regimiento.getUnidad().getNombre() + " ["
                        + regimiento.getPuntos() + " pts]";
                ListItem listItem = new ListItem(text);
                PdfPTable table = new PdfPTable(9);
                table.addCell("M");
                table.addCell("HA");
                table.addCell("HP");
                table.addCell("F");
                table.addCell("R");
                table.addCell("H");
                table.addCell("I");
                table.addCell("A");
                table.addCell("L");
                table.addCell(String.valueOf(regimiento.getUnidad().getMovimiento()));
                table.addCell(String.valueOf(regimiento.getUnidad().getHabilidadArmas()));
                table.addCell(String.valueOf(regimiento.getUnidad().getHabilidadProyectiles()));
                table.addCell(String.valueOf(regimiento.getUnidad().getFuerza()));
                table.addCell(String.valueOf(regimiento.getUnidad().getResistencia()));
                table.addCell(String.valueOf(regimiento.getUnidad().getHeridas()));
                table.addCell(String.valueOf(regimiento.getUnidad().getIniciativa()));
                table.addCell(String.valueOf(regimiento.getUnidad().getAtaques()));
                table.addCell(String.valueOf(regimiento.getUnidad().getLiderazgo()));
                listItem.add(table);
                list.add(listItem);
            }
            document.add(list);
        }
    }


    /**
     * Crea el directorio para guardar los PDF generados
     *
     * @return directorio
     */
    @NonNull
    private static File createDirectoryIfNotExists() {
        String path = Environment.getExternalStorageDirectory() + "/" + Constants.DIR_FICHEROS;
        Log.i("Path", path);
        File dir = new File(path);
        if(!dir.exists()) {
            if (dir.mkdir()) {
                Log.i("DIR", "directorio creado");
            } else {
                Log.e("DIR", "no se pudo crear el directorio");
            }
        }
        return dir;
    }


    /**
     * Empieza un activity para ver el PDF
     *
     * @param activity
     * @param fileName
     */
    public static void viewPDF(MainActivity activity, String fileName) {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + Constants.DIR_FICHEROS + "/" + fileName);
        Uri path = Uri.fromFile(pdfFile);

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            activity.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }
}
