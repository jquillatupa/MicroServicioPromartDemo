package com.example.demo;

public class Customer {

    private  String Nombre;
    private  String Apellido;
    private  double Edad;
    private String FechaNacimiento;
    private  String FechaProbableMuerte;

    public String getFechaProbableMuerte() {
        return FechaProbableMuerte;
    }

    public void setFechaProbableMuerte(String fechaProbableMuerte) {
        FechaProbableMuerte = fechaProbableMuerte;
    }

    public Customer()
    {}

    public Customer(String Nombre,String Apellido, int Edad, String FechaNacimiento) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Edad = Edad;
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public double getEdad() {
        return Edad;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setEdad(double edad) {
        Edad = edad;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }
}
