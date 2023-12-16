package com.example.hello_world2.File_Resources;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;

import com.example.hello_world2.model.Bays;

import java.util.ArrayList;
import java.util.Date;

public class PDFConverter {
  /* private int pdf_width;
    private int pdf_height;*/

    private String subTitle;
    private String mainTitle;
    private String userName;

    private int pageNumber;
    private Bays bays;
    private boolean isFinished;

    private int rowCount;
    private PdfDocument document;
    private String name;
    private String date;
    private Paint title;
    private Paint myPaint;
    private Paint bayInfo;
    private String deptNum;
    private String articleNum;
    private String deptName;
    private String aliseNum;
    private String bayLocation;
    private Canvas canvas;
    private PdfDocument.Page page;


    public PDFConverter(String mainTitle, String subTitle)
    {
         document = new PdfDocument();
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.pageNumber = 1;
        isFinished= false;
        rowCount = 0;
        bays = new Bays("Kevin H.");
        name = bays.getUserName();
        date = new Date().toString();
        title = new Paint();
        myPaint = new Paint();
       bayInfo = new Paint();
        deptNum = "";
        articleNum = "";
        deptName = "";
        aliseNum = "";
        bayLocation = "";
        canvas = null;
    }


   /* public void setPdfWidth(int width)
    {
        pdf_width = width;
    }

    public void setPdfHeight(int height)
    {
        pdf_height = height;
    }

    public int getPdfWidth()
    {
        return pdf_width;
    }

    public int getpdfHeight()
    {
        return pdf_height;
    }*/

    public PdfDocument convert2Pdf(int pdf_width, int pdf_height)
    {
        Bays bays = new Bays();

        int rowsPerPage = 3;


        int numberOfBays = Bays.get_bay_list().size();

            // create a pa
        if(numberOfBays <= rowsPerPage)
        {
            //pageNumber = 1;
            this.createPage(pageNumber);
            int y_axis = 690;
            for(int i = 0; i < rowsPerPage;++i) {
                canvas.drawText(Bays.get_bay_list().get(i).getDeptNumber(), 85, y_axis, bayInfo);
                canvas.drawText(Bays.get_bay_list().get(i).getArticleNumber(), 350, y_axis, bayInfo);
                canvas.drawText(Bays.get_bay_list().get(i).getDeptName(), 650, y_axis, bayInfo);
                canvas.drawText(Integer.toString(Bays.get_bay_list().get(i).getAliseNumber()), 850, y_axis, bayInfo);
                canvas.drawText(Bays.get_bay_list().get(i).getBaylocation(), 1100, y_axis, bayInfo);
                y_axis = y_axis + 40;
            }
            document.finishPage(page);
        }
        else
        {
              pageNumber =  numberOfBays / rowsPerPage;

              int count = 0;
              for(int i = 0; i < pageNumber; ++i)
              {
                  this.createPage(i+1);
                  int y_axis = 690;
                  for (int k = 0; k < 3; ++k)
                  {
                      canvas.drawText(Bays.get_bay_list().get(count).getDeptNumber(), 85, y_axis, bayInfo);
                      canvas.drawText(Bays.get_bay_list().get(count).getArticleNumber(), 350, y_axis, bayInfo);
                      canvas.drawText( Bays.get_bay_list().get(count).getDeptName(), 650, y_axis, bayInfo);
                      canvas.drawText(Integer.toString(Bays.get_bay_list().get(count).getAliseNumber()), 850, y_axis, bayInfo);
                      canvas.drawText(Bays.get_bay_list().get(count).getBaylocation(), 1100, y_axis, bayInfo);
                      y_axis = y_axis + 40;
                      ++count;
                  }

                  document.finishPage(page);
              }
                 if(numberOfBays - rowsPerPage != 0)
                 {
                     int y_axis = 690;
                     this.createPage(++pageNumber);
                     for(int i = 0; i < numberOfBays%3; ++i)
                     {
                         canvas.drawText(Bays.get_bay_list().get(count).getDeptNumber(), 85, y_axis, bayInfo);
                         canvas.drawText(Bays.get_bay_list().get(count).getArticleNumber(), 350, y_axis, bayInfo);
                         canvas.drawText( Bays.get_bay_list().get(count).getDeptName(), 650, y_axis, bayInfo);
                         canvas.drawText(Integer.toString(Bays.get_bay_list().get(count).getAliseNumber()), 850, y_axis, bayInfo);
                         canvas.drawText(Bays.get_bay_list().get(count).getBaylocation(), 1100, y_axis, bayInfo);
                         y_axis = y_axis + 40;
                         ++count;
                     }
                     document.finishPage(page);
                 }
        }
            return document;
    }

    private void createPage(int number)
    {
        int pdf_width =1200;
        int pdf_height = 2010;
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pdf_width, pdf_height, number).create();

        // start a page
         page = document.startPage(pageInfo);

        // draw something on the page
        // View content = findViewById(R.id.table);
        canvas = page.getCanvas();

        title.setTextAlign(Paint.Align.CENTER);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);
        canvas.drawText(mainTitle, pdf_width / 2, 270, title);

        title.setTextAlign(Paint.Align.CENTER);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        title.setTextSize(45);
        canvas.drawText("(" + subTitle + ")", pdf_width / 2, 320, title);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        myPaint.setTextSize(20);
        myPaint.setColor(Color.BLACK);
        canvas.drawText("Created By:" + userName, 1180, 390, myPaint);
        canvas.drawText("Created on:" + date, 1180, 430, myPaint);

        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20, 580, pdf_width - 20, 660, myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);

        canvas.drawText("Dept. Number", 40, 640, myPaint);
        canvas.drawText("Article Number", 200, 640, myPaint);
        canvas.drawText("Dept. Name", 550, 640, myPaint);
        canvas.drawText("Alsie Number", 800, 640, myPaint);
        canvas.drawText("Bay Location", 1030, 640, myPaint);

        canvas.drawLine(180, 590, 180, 640, myPaint);
        canvas.drawLine(180, 590, 180, 640, myPaint);
        canvas.drawLine(180, 590, 180, 640, myPaint);
        canvas.drawLine(180, 590, 180, 640, myPaint);

        bayInfo.setTextAlign(Paint.Align.RIGHT);
        bayInfo.setTypeface(Typeface.DEFAULT);
        bayInfo.setTextSize(20);
        bayInfo.setColor(Color.BLACK);

        /*for(; i < numberOfBays; ++i)
        {*/

           /* canvas.drawText(deptNum, 85, y_axis, bayInfo);
            canvas.drawText(articleNum, 350, y_axis, bayInfo);
            canvas.drawText(deptName, 650, y_axis, bayInfo);
            canvas.drawText(aliseNum, 850, y_axis, bayInfo);
            canvas.drawText(bayLocation, 1100, y_axis, bayInfo);
            y_axis = y_axis + 40;*/







        /*canvas.drawText( "27", 85, count, bayInfo);
        canvas.drawText("1001-234-567", 350, count, bayInfo);
        canvas.drawText("ELECTRICAL", 650, count, bayInfo);
        canvas.drawText("22", 850, count, bayInfo);
        canvas.drawText("22-001", 1100, count, bayInfo);*/

        //document.finishPage(page);
    }


}
