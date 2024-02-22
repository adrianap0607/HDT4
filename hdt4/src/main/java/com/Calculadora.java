//Nombre: Bryan Alberto Martínez Orellana
//Carné: 23542
//Nombre: Adriana Sophia Palacios Contreras
//Carné: 23044
//Fecha de Creación: 14/02/2024
//Fecha de Última Modificacion: 21/02/2024

package com;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;

/**
 * Clase que representa una calculadora capaz de realizar operaciones
 * aritméticas utilizando distintas estructuras de datos.
 */
public class Calculadora {
    
    private static ListaFactory listaFactory = new ListaFactory();
    private static PilaFactory pilaFactory = new PilaFactory();
    // Instancia única de la calculadora
    private static Calculadora instancia;

    
    /** 
     * @param caracter
     * @return boolean
     */
    // Método para verificar si un carácter es un dígito
    public static boolean esDigito(char caracter) {
        return Character.isDigit(caracter);
    }

    
    /** 
     * @return Calculadora
     */
    // Método estático para obtener la instancia única de la calculadora
    public static Calculadora obtenerInstancia() {
        // Si la instancia aún no ha sido creada, la crea
        if (instancia == null) {
            instancia = new Calculadora();
        }
        // Devuelve la instancia única de la calculadora
        return instancia;
    }

    
    /** 
     * @param texto
     * @return ArrayList<String>
     */
    public static ArrayList<String> Separacion(String texto) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            // Convertir cada carácter en una cadena y agregarlo al ArrayList
            strings.add(String.valueOf(texto.charAt(i)));
        }
        return strings;
    }

    
    /** 
     * @param Postfix
     * @param seleccion
     * @param tipoADT
     * @return int
     */
    public static int calcular(String Postfix, String seleccion, String tipoADT){
        
        ArrayList<String> texto = Separacion(Postfix);

        // Usamos un iterador para recorrer la lista y eliminar los elementos que sean espacios
        Iterator<String> iterador = texto.iterator();
        while (iterador.hasNext()) {
            String elemento = iterador.next();
            if (elemento.equals(" ")) {
                iterador.remove(); // Eliminamos el elemento actual del iterador y de la lista
            }
        }

        //Se crean las variables ha implementar
        int resultado = 0;
        boolean operadorValido = true;
        int contadorDigitos = 0;
        int contadorOperandos = 0;

        if (tipoADT.equals("STACK")){
            PilaInterface<Integer> adt = pilaFactory.crearLista(seleccion);
            
            // Contamos la cantidad de dígitos y operandos
            for(String elemento: texto){
                for(char c: elemento.toCharArray()){
                    if(esDigito(c)){
                        contadorDigitos = 2;
                    } else{
                        contadorOperandos = 1;
                    }
                }
            }

            // Verificamos si la cantidad de dígitos es uno más que la de operandos
            if(contadorDigitos - contadorOperandos == 1){
                for(String elemento: texto){
                    //Verifica que no se encuentre un operador que no sea válido
                    if(!operadorValido){
                        break;
                    }
                    //Se recorren todos los elementos una vez más para hacer las operaciones
                    for(char caracter: elemento.toCharArray()){
                        System.out.println(caracter);
                        //Si es un dígito lo pushea al Vector de la clase Pila
                        if(esDigito(caracter)){
                            adt.push(Integer.parseInt(String.valueOf(caracter)));
                        } else{ //De lo contrario, si se topa con una operador, llama a los 2 números de encima, y hace la operación
                            System.out.println("--------------------");
                            int val1 = adt.pop();
                            System.out.println("Primer valor tomado: " + val1);
                            int val2 = adt.pop();
                            System.out.println("Segundo valor tomado: " + val2);
                            System.out.println("Operacion a Hacer: " + caracter);
                            System.out.println("--------------------");
                            //Decide la operación correcta a emplear
                            if(caracter == '+'){
                                resultado = val2 + val1;
                                adt.push(resultado);
                            } else if(caracter == '-'){
                                resultado = val2 - val1;
                                adt.push(resultado);
                            } else if(caracter == '*'){
                                resultado = val2 * val1;
                                adt.push(resultado);
                            } else if(caracter == '/'){
                                //Validación para división por 0
                                if(val1 == 0){
                                    System.out.println("Se reemplazará el 0 por un 1, ya que no se puede dividir por 0.");
                                    val1 = 1;
                                }
                                resultado = val2/val1;
                                adt.push(resultado);
                            } else{ //Validación si se encuentra un signo no válido
                                System.out.println("Ha ingresado un operador no válido :(");
                                System.out.println("Se procederá a darle el resultado actual, revise su expresión.");
                                operadorValido = false;
                            }
                            System.out.println("RESULTADO: " + resultado);
                            System.out.println("--------------------");
                            System.out.println();
                        }
                    }
                }
            } else{
                System.out.println("No se cuenta con la cantidad adecuada de dígitos u operadores.");
            }
        } else if(tipoADT.equals("LISTA")){
            ListaInterface<Integer> adt = listaFactory.crearLista(seleccion);
                
            // Contamos la cantidad de dígitos y operandos
            for(String elemento: texto){
                for(char c: elemento.toCharArray()){
                    if(esDigito(c)){
                        contadorDigitos++;
                    } else{
                        contadorOperandos++;
                    }
                }
            }
            // Verificamos si la cantidad de dígitos es uno más que la de operandos
            if(contadorDigitos - contadorOperandos == 1){
                for(String elemento: texto){
                    //Verifica que no se encuentre un operador que no sea válido
                    if(!operadorValido){
                        break;
                    }
                    //Se recorren todos los elementos una vez más para hacer las operaciones
                    for(char caracter: elemento.toCharArray()){
                        //Si es un dígito lo pushea al Vector de la clase Pila
                        if(esDigito(caracter)){
                            adt.push(Integer.parseInt(String.valueOf(caracter)));
                        } else{ //De lo contrario, si se topa con una operador, llama a los 2 números de encima, y hace la operación
                            System.out.println("--------------------");
                            int val1 = adt.pop();
                            System.out.println("Primer valor tomado: " + val1);
                            int val2 = adt.pop();
                            System.out.println("Segundo valor tomado: " + val2);
                            System.out.println("Operacion a Hacer: " + caracter);
                            System.out.println("--------------------");
                            //Decide la operación correcta a emplear
                            if(caracter == '+'){
                                resultado = val2 + val1;
                                adt.push(resultado);
                            } else if(caracter == '-'){
                                resultado = val2 - val1;
                                adt.push(resultado);
                            } else if(caracter == '*'){
                                resultado = val2 * val1;
                                adt.push(resultado);
                            } else if(caracter == '/'){
                                //Validación para división por 0
                                if(val1 == 0){
                                    System.out.println("Se reemplazará el 0 por un 1, ya que no se puede dividir por 0.");
                                    val1 = 1;
                                }
                                resultado = val2/val1;
                                adt.push(resultado);
                            } else{ //Validación si se encuentra un signo no válido
                                System.out.println("Ha ingresado un operador no válido :(");
                                System.out.println("Se procederá a darle el resultado actual, revise su expresión.");
                                operadorValido = false;
                            }
                            System.out.println("RESULTADO: " + resultado);
                            System.out.println("--------------------");
                            System.out.println();
                        }
                    }
                }
            } else{
                System.out.println("No se cuenta con la cantidad adecuada de dígitos u operadores.");
            }
        }

        return resultado;
    }
}
