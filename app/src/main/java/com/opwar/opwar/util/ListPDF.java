package com.opwar.opwar.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opwar.opwar.activities.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by URZU on 09/05/2016.
 */
public class ListPDF {
    // Method for creating a pdf file from text, saving it then opening it for display
    public static String createAndDisplayPDF(String text) {
        Document doc = new Document();

        try {
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

            File file = new File(dir, "newFile.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

            Paragraph p1 = new Paragraph(text);
            Font paraFont= new Font(Font.FontFamily.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);
            //add paragraph to document
            doc.add(p1);

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally {
            doc.close();
        }

        return "newFile.pdf";
    }


    // Method for opening a pdf file
    public static void viewPDF(MainActivity activity, String file) {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + Constants.DIR_FICHEROS + "/" + file);
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
