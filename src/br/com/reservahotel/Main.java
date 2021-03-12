package br.com.reservahotel;

import br.com.reservahotel.domain.Estadia;
import br.com.reservahotel.domain.Hotel;
import br.com.reservahotel.domain.TipoCliente;
import br.com.reservahotel.service.ReservaHotelService;

import java.util.ArrayList;
import java.util.List;

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

        List<Hotel> listaHoteis = new ArrayList<>();
        listaHoteis.add(lakewood);
        listaHoteis.add(bridgewood);
        listaHoteis.add(ridgewood);

        ReservaHotelService reservaHotelService = new ReservaHotelService();
        reservaHotelService.getMelhorCustoBeneficio(listaHoteis);
    }
}
