package com.example.demo;

public class kpicliente {
    private  double promedio;
    private  double desviacionstandar;

    public kpicliente(double promedio, double desviacionstandar) {
        this.promedio = promedio;
        this.desviacionstandar = desviacionstandar;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getDesviacionstandar() {
        return desviacionstandar;
    }

    public void setDesviacionstandar(double desviacionstandar) {
        this.desviacionstandar = desviacionstandar;
    }

    public kpicliente(){}

}
