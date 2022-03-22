package uy.com.sofka.dojoreactividad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DojoReactividadApplication {

  public static Flux<Correo> crearListaConCorreos(){
    List<Correo> lista = new ArrayList<Correo>();
    lista.add(new Correo(1, "correo1@gmail.com", false));
    lista.add(new Correo(2, "correo2@hotmail.com", false));
    lista.add(new Correo(3, "correo3@outlook.com", false));
    lista.add(new Correo(4, "correo4@email.com", false));
    lista.add(new Correo(5, "correo5@hotmail.com.uy", false));
    lista.add(new Correo(6, "correo6outlook.com", false));
    lista.add(new Correo(7, "correo7@gmailcom", false));
    lista.add(new Correo(8, "correo8@.com", false));
    lista.add(new Correo(9, "correo9@email2.com", false));
    lista.add(new Correo(10, "correo10@gmail.com", false));
    lista.add(new Correo(11, "correo11@hotmail.com", false));
    lista.add(new Correo(12, "correo12@outlook.com", false));
    lista.add(new Correo(13, "correo13@gmail.com", false));
    lista.add(new Correo(14, "correo14@hotmail.com", false));
    lista.add(new Correo(15, "correo15@outlook.com", false));
    lista.add(new Correo(16, "correo16@gmail.com", false));
    lista.add(new Correo(17, "correo17@hotmail.com", false));
    lista.add(new Correo(18, "correo18@outlook.com", false));
    lista.add(new Correo(19, "correo19@gmail.com", false));
    lista.add(new Correo(20, "correo20@hotmail.com", false));
    lista.add(new Correo(21, "correo1@outlook.com", false));
    lista.add(new Correo(22, "correo2@gmail.com", false));
    lista.add(new Correo(23, "correo3@hotmail.com", false));
    lista.add(new Correo(24, "correo4@outlook.com", false));
    lista.add(new Correo(25, "correo5@gmail.com", false));
    lista.add(new Correo(26, "correo6@hotmail.com", false));
    lista.add(new Correo(27, "correo7@outlook.com", false));
    lista.add(new Correo(28, "correo8@gmail.com", false));
    lista.add(new Correo(29, "correo9@hotmail.com", false));
    lista.add(new Correo(30, "correo10@outlook.com", false));

    return Flux.fromIterable(lista);
  }

  private static Boolean isValid(String cadena) {
    var p = Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
    + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$");

    return p.matcher(cadena).find();
  }

  public static void main(String[] args) {
		SpringApplication.run(DojoReactividadApplication.class, args);

    Flux<Correo> lista = crearListaConCorreos();
    System.out.println("Lista original:");
    lista.subscribe(correo -> {
      System.out.println("Estado del correo" + correo.getUrl() + " : " + correo.getEstado() + "");
    });

    // Distinct url de correo repetida
    Flux<Correo> listaSinIguales = lista.distinct(correo -> correo.getUrl());
    System.out.println("\nLista de correos distintos:");
    listaSinIguales.subscribe(System.out::println);
    
    // Filtrar por url gmail
    Flux<Correo> listaGmail = lista.filter(correo -> correo.getUrl().contains("@gmail."));
    System.out.println("\nLista de correos filtrados por gmail:");
    listaGmail.subscribe(System.out::println);
    
    // Filtrar por url hotmail
    Flux<Correo> listaHotmail = lista.filter(correo -> correo.getUrl().contains("@hotmail."));
    System.out.println("\nLista de correos filtrados por hotmail:");
    listaHotmail.subscribe(System.out::println);
    
    // Filtrar por url outlook
    Flux<Correo> listaOutlook = lista.filter(correo -> correo.getUrl().contains("@outlook."));
    System.out.println("\nLista de correos filtrados por outlook:");
    listaOutlook.subscribe(System.out::println);
    
    // Mapear para saber si el correo es valido
    Flux<Map<Correo, Boolean>> listaValida = lista.map(correo -> {
      if(isValid(correo.getUrl()))
        return Map.of(correo, true);
      return Map.of(correo, false);
    });

    System.out.println("\nLista de correos validos:");
    listaValida.subscribe(mapa -> {
      mapa.forEach((correo, valido) -> {
      if(valido)
        System.out.println("Correo VALIDO: " + correo.toString());
      else 
        System.out.println("Correo INVALIDO: " + correo.toString());
        });
    });
    
    // Tamaño de la lista de correos
    Mono<Long> cantidadLista = lista.count();
    cantidadLista.subscribe(cantidad -> System.out.println("\nLa cantidad de correos en la lista es: " + cantidad));
    
    // // Tamaño de la lista de correos filtrados por url gmail
    Mono<Long> cantidadListaGmail = lista.filter(correo -> correo.getUrl().contains("@gmail.")).count();
    cantidadListaGmail.subscribe(cantidad -> System.out.println("La cantidad de correos en la lista es: " + cantidad));
    
    // // Tamaño de la lista de correos filtrados por url hotmail
    Mono<Long> cantidadListaHotmail = lista.filter(correo -> correo.getUrl().contains("@hotmail.")).count();
    cantidadListaHotmail.subscribe(cantidad -> System.out.println("La cantidad de correos en la lista es: " + cantidad));
    
    // // Tamaño de la lista de correos filtrados por url outlook
    Mono<Long> cantidadListaOutlook = lista.filter(correo -> correo.getUrl().contains("@outlook.")).count();
    cantidadListaOutlook.subscribe(cantidad -> System.out.println("La cantidad de correos en la lista es: " + cantidad));

    // Cambiar estado de los correos a enviado
    lista.subscribe(correo -> {
      correo.setEstado(true);
      System.out.println("Estado del correo" + correo.getUrl() + " : " + correo.getEstado() + "");
    });

	}

}
