package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.valves.HealthCheckValve;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/customers/")
@Api(value = "CustomerControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<>();
        customers.add(new Customer("Juan","Perez",30,"07/06/1989"));
        customers.add(new Customer("Pedro","Gutierrez",60,"07/10/1985"));
        customers.add(new Customer("Luis","Avalos",80,"03/01/1982"));
        customers.add(new Customer("Antony","Echevarria",25,"07/06/1984"));
        customers.add(new Customer("William","Arizapana",20,"07/10/1985"));
        customers.add(new Customer("Luis","Gallegos",55,"03/01/1980"));
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Customer> getAll() {
        return customers;
    }
    @ApiOperation("Get the List KPI Customers")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = Customer.class)})
    @RequestMapping(value = "/kpideclientes" , method = RequestMethod.GET)
    public kpicliente kpideclientes() {
        Double[] listaEdades = new Double[customers.size()];
        //contador
        int i=0;
        for (Customer customer:customers) {

            listaEdades[i]=(customer.getEdad());
            i++;
        }
        kpicliente rkpicliente= new kpicliente();
        rkpicliente.setPromedio(hallarPromedio(listaEdades));
        rkpicliente.setDesviacionstandar(hallarDesviacionEstandar(listaEdades));
        return rkpicliente;
    }
    @RequestMapping(value = "/listclientes" , method = RequestMethod.GET)
    public List<Customer> listclientes() {
        for (Customer customer:customers) {

           customer.setFechaProbableMuerte("10/12/2060");

        }
        return customers;
    }
    @RequestMapping(value="/create",method = RequestMethod.POST)
    public  List<Customer> create(@RequestBody Customer customer) {
        customers.add(customer);
        return customers;
    }

    public Double hallarDesviacionEstandar(Double[] cadenaDeNumeros){
        Double desviacionEstandar = 0.0;
        if(cadenaDeNumeros == null){
            System.out.println("la cadena de numeros es nula, no puedo hallar la desviacion estandar");
            return null;
        }
        //***hallar cantidad de datos
        Integer qNumeros = cadenaDeNumeros.length;
        //***hallar Promedio
        Double promedio = this.hallarPromedio(cadenaDeNumeros);
//        System.out.println("Promedio "+promedio);
        //***hallar el cuadrado del valor absoluto de la distancia de cada dato al promedio
        Double[] cuadradoDelAbsolutoDeLasdistanciasAlPromedio = this.hallarCuadradoDelValorAbsolutoDeLasDistanciasAlPromedio(cadenaDeNumeros, promedio);
        //***hallar la sumatoria del conjunto de datos resultado del anterior paso
        Double sumatoriaDelCuadradoDelAbsolutoDeLaDist = this.hallarSumatoria(cuadradoDelAbsolutoDeLasdistanciasAlPromedio);
//        System.out.println("sumatoriaCuadrados "+sumatoriaDelCuadradoDelAbsolutoDeLaDist);
        //***dividir la sumatoria sobre la cantidad de datos dada al inicio
        Double divisionSumatoriaCuadradoYQNumeros = sumatoriaDelCuadradoDelAbsolutoDeLaDist / qNumeros;
//        System.out.println("divisionSumatoriaCuadrados "+divisionSumatoriaCuadradoYQNumeros);
        //***hallar la raiz cuadrada de ese valor
        desviacionEstandar = Math.sqrt(divisionSumatoriaCuadradoYQNumeros);
        return desviacionEstandar;
    }

    public Double hallarSumatoria(Double[] distanciasAlpromedio){
        if(distanciasAlpromedio == null){
            System.out.println("Error, las distanciasAlpromedio es nula, no puedo hallar la sumatoria");
            return null;
        }
        Double sumatoria = 0.0;
        for (int i = 0; i < distanciasAlpromedio.length; i++) {
            Double distancia = distanciasAlpromedio[i];
            sumatoria += distancia;
        }
        return sumatoria;
    }
    public Double hallarPromedio(Double[] cadenaDeNumeros){
        if(cadenaDeNumeros == null){
            System.out.println("la cadena de numeros es nula, no puedo hallar elpromedio");
            return null;
        }
        //***hallar cantidad de datos
        Integer qNumeros = cadenaDeNumeros.length;
        Double sumatoria = 0.0;
        //***hallar sumatoria
        sumatoria = this.hallarSumatoria(cadenaDeNumeros);
        //***hallar Promedio
        Double promedio = sumatoria / qNumeros;
        return promedio;
    }

    public Double[] hallarCuadradoDelValorAbsolutoDeLasDistanciasAlPromedio(Double[] cadenaDeNumeros, Double promedio){
        if(cadenaDeNumeros == null){
            System.out.println("la cadena de numeros es nula, no puedo hallar la distancia al promedio");
            return null;
        }
        Double[] distanciasAlPromedio = new Double[cadenaDeNumeros.length];
        for (int i = 0; i < cadenaDeNumeros.length; i++) {
            Double numeroActual = cadenaDeNumeros[i];
            Double distancia = numeroActual - promedio;
//            System.out.println("distancia "+distancia);
            Double distanciaAbsoluto = Math.abs(distancia);
            Double cuadradoDelAbsoluto = Math.pow(distanciaAbsoluto, 2);
            distanciasAlPromedio[i] = cuadradoDelAbsoluto;
//            System.out.println("cuadradoDelAbsoluto "+cuadradoDelAbsoluto);
        }
        return distanciasAlPromedio;
    }

}
