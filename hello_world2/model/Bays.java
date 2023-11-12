package com.example.hello_world2.model;
import java.util.ArrayList;

public class Bays  {


  private int deptNumber;
  private String deptName;
  private int aliseNumber;
   private String baylocation;

   static private ArrayList<Bays> bayList = new ArrayList<Bays>();
   private int id;

   private boolean isChecked;

    public Bays()
    {
        deptNumber = 0;
        deptName = "";
        aliseNumber = 0;
        baylocation = "";
        id = 0;
        isChecked = false;

    }

    public String getBaylocation() {
        return baylocation;
    }

    public int getAliseNumber() {
        return aliseNumber;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }

    public void setBaylocation(String baylocation) {
        this.baylocation = baylocation;
    }

    public void setAliseNumber(int aliseNumber) {
        this.aliseNumber = aliseNumber;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public void setId(int id){ this.id = id;}

    public int getId(){ return this.id;}
   static public ArrayList<Bays> get_bay_list(){return bayList;}
    static public void set_bay_list(ArrayList<Bays> bayList){Bays.bayList = bayList;}

    public void set_isChecked(boolean isChecked){ this.isChecked = isChecked;}

    public boolean isChecked() {
        return isChecked;
    }
}
