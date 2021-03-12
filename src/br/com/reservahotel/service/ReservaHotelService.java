package br.com.reservahotel.service;

import br.com.reservahotel.domain.Estadia;
import br.com.reservahotel.domain.Hotel;
import br.com.reservahotel.domain.TipoCliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservaHotelService {

    public void getMelhorCustoBeneficio(List<Hotel> listaHoteis){

        TipoCliente tipoCliente = getTipoCliente();

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

    private void calcularTotal(List<Hotel> listaHoteis, TipoCliente tipoCliente) {
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
            //ln("HOTEL: " + hotel.getNome() + " - R$"+hotel.getTotal());
        }

        Hotel hotelMaisBarato = new Hotel();
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
        ln("Melhor custo benefício: " + hotelMaisBarato.getNome());
        ln("Preço total: " + hotelMaisBarato.getTotal());
        ln("Classificação: " + hotelMaisBarato.getClassificacao());
        separator();

    }

    private boolean isFinalSemana(String dia){
        switch (dia){
            case "SATURDAY":
            case "SUNDAY":
                return true;
            default:
                return false;
        }
    }

    private TipoCliente getTipoCliente() {
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

    private String getDiaSemana(int dia) {
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

    private void ln(Object value) {
        System.out.println(value.toString());
    }

    private void separator(){
        System.out.println("==================================================================");
    }
}
