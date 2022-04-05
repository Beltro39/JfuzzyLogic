/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import net.sourceforge.jFuzzyLogic.FIS;
import interfaz.homeUI;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
/**
 *
 * @author sbeltrana
 */
public class FIS_AirQuality {
    public static void main(String[] args) {

        homeUI p = new homeUI();
        p.setVisible(true);

    }
    
    public String calcularAirQuality(double pm25, double pm10, double o3){
        String fileName = "src/logica/FIS_AirQuality.fcl";
        FIS fis = FIS.load(fileName, true);
        
        
        // En caso de error
        if (fis == null) {
            System.err.println("No se puede cargar el archivo: '" + fileName + "'");
            return "";
        }
        fis.setVariable("concentracion_pm25", pm25);
        fis.setVariable("concentracion_pm10", pm10);
        fis.setVariable("concentracion_o3", o3);
        
         fis.evaluate();

        // Muestra los gráficos de las variables de entrada y salida
        JFuzzyChart.get().chart(fis.getFunctionBlock("prop"));
        
        double salida = fis.getVariable("tiempo_duracion").getLatestDefuzzifiedValue();
        double salida2 = fis.getVariable("tipo_episodio").getLatestDefuzzifiedValue();
        
        String recomendacion = "";
        recomendacion = "promedio";
        /*
        return ("Porcentaje de esperanza de vida: " + String.format("%.1f", salida) + "%" +" de probabilidad de sobrevivir a la enfermedad."+
               "\n" + "Precio de quimioterapia: " +String.format("%.1f", salida2)+" Millones de pesos."+"\n"+
                "Te recomendamos comenzar con las quimioterapias lo mas pronto posible para que puedas recupearte."+
                "\n"+"Espero haberte ayudado, se despide tu chatbot Bibi."+
                "\n"+"");
        */
        return ("Tiempo_duracion: "+ String.format("%.1f", salida)+ " horas\n Tipo_episodio: "+ String.format("%.1f", salida2)+" %");
    }
}
