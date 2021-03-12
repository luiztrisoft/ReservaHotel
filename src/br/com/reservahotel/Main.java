package br.com.reservahotel;

import br.com.reservahotel.domain.Estadia;
import br.com.reservahotel.domain.Hotel;
import br.com.reservahotel.domain.TipoCliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Estadia> estadiasLakewood = new ArrayList<>();
        estadiasLakewood.add(new Estadia(TipoCliente.REGULAR, 110D, 90D));
        estadiasLakewood.add(new Estadia(TipoCliente.REWARD, 80D, 80D));
        Hotel lakewood = new Hotel("Lakewood", 3, estadiasLakewood);

        List<Estadia> estadiasBridgewood = new ArrayList<>();
        estadiasBridgewood.add(new Estadia(TipoCliente.REGULAR, 160D, 60D));
        estadiasBridgewood.add(new Estadia(TipoCliente.REWARD, 110D, 50D));
        Hotel bridgewood = new Hotel("Bridgewood", 4, estadiasBridgewood);

        List<Estadia> estadiasRidgewood = new ArrayList<>();
        estadiasRidgewood.add(new Estadia(TipoCliente.REGULAR, 220D, 150D));
        estadiasRidgewood.add(new Estadia(TipoCliente.REWARD, 100D, 40D));
        Hotel ridgewood = new Hotel("Ridgewood", 5, estadiasRidgewood);

        TipoCliente tipoCliente = getTipoCliente();

        List<Hotel> listaHoteis = new ArrayList<>();
        listaHoteis.add(lakewood);
        listaHoteis.add(bridgewood);
        listaHoteis.add(ridgewood);

        if(tipoCliente.equals(TipoCliente.REWARD)){
            separator();
            ln("Tipo escolhido: FIDELIDADE");
            separator();
            calcularTotal( listaHoteis, TipoCliente.REWARD);

        }else{
            separator();
            ln("Tipo escolhido: REGULAR");
            separator();
            calcularTotal( listaHoteis, TipoCliente.REGULAR);
        }
    }

    private static void calcularTotal(List<Hotel> listaHoteis, TipoCliente tipoCliente) {
        List<String > dias = new ArrayList<>();
        dias.add(getDiaSemana(1));
        dias.add(getDiaSemana(2));
        dias.add(getDiaSemana(3));

        for (Hotel hotel : listaHoteis){
            for (String dia: dias) {
                Estadia estadia = hotel.getEstadias().stream().filter(es -> es.getTipoCliente().equals(tipoCliente)).findFirst().get();
                if (isFinalSemana(dia)) {
                    hotel.setTotal(hotel.getTotal() + estadia.getTaxaFimSemana());
                } else {
                    hotel.setTotal(hotel.getTotal() + estadia.getTaxaDiaSemana());
                }
            }
            ln("HOTEL: " + hotel.getNome() + " - R$"+hotel.getTotal());
        }


        Hotel hotelMaisBarato = new Hotel();
//        Hotel h1 = new Hotel("H 1", 2, null);
//        h1.setTotal(2000D);
//
//        Hotel h2 = new Hotel("H 2", 3, null);
//        h2.setTotal(1000D);
//
//        Hotel h3 = new Hotel("H 3", 5, null);
//        h3.setTotal(2000D);
//
//        listaHoteis = new ArrayList<>();
//        listaHoteis.add(h1);
//        listaHoteis.add(h2);
//        listaHoteis.add(h3);

        for (Hotel hotel : listaHoteis){
            if(hotelMaisBarato.getTotal() == 0D) {
                hotelMaisBarato = hotel;
            }
            if(hotel.getTotal().equals(hotelMaisBarato.getTotal()) && hotel.getClassificacao() > hotelMaisBarato.getClassificacao()){
                hotelMaisBarato = hotel;
            }
            if(hotel.getTotal() < hotelMaisBarato.getTotal()){
                hotelMaisBarato = hotel;
            }
        }

        separator();
        ln("Opção mais barata: " + hotelMaisBarato.getNome());
        separator();

    }

    private static boolean isFinalSemana(String dia){
        switch (dia){
            case "SATURDAY":
            case "SUNDAY":
                return true;
            default:
                return false;
        }
    }

    private static TipoCliente getTipoCliente() {
        ln("Digite o tipo de cliente: (Regular ou Reward) ");
        Scanner sc = new Scanner(System.in);
        String tipoCliente = sc.next();

            if(tipoCliente.equalsIgnoreCase("regular")){
                return TipoCliente.REGULAR;
            }else if(tipoCliente.equalsIgnoreCase("reward")) {
                return TipoCliente.REWARD;
            }
        return getTipoCliente();
    }

    private static String getDiaSemana(int dia) {
        StringBuilder sb = new StringBuilder();
        sb.append("Digite a data ").append(dia).append(" no formato DD/MM/AAAA:");
        ln(sb);
        Scanner scannerData = new Scanner(System.in);
        String data = scannerData.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(data, formatter);
            return localDate.getDayOfWeek().toString();
        }catch (Exception e){
            separator();
            ln("Formato de data inválido! " + e.getMessage());
            separator();
            return getDiaSemana(dia);
        }
    }

    private static void ln(Object value) {
        System.out.println(value.toString());
    }

    private static void separator(){
        System.out.println("==================================================================");
    }
}
