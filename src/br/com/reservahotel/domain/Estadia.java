package br.com.reservahotel.domain;

public class Estadia {
    private TipoCliente tipoCliente;
    private Double taxaDiaSemana;
    private Double taxaFimSemana;

    public Estadia(TipoCliente tipoCliente, Double taxaDiaSemana, Double taxaFimSemana) {
        this.tipoCliente = tipoCliente;
        this.taxaDiaSemana = taxaDiaSemana;
        this.taxaFimSemana = taxaFimSemana;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Double getTaxaDiaSemana() {
        return taxaDiaSemana;
    }

    public void setTaxaDiaSemana(Double taxaDiaSemana) {
        this.taxaDiaSemana = taxaDiaSemana;
    }

    public Double getTaxaFimSemana() {
        return taxaFimSemana;
    }

    public void setTaxaFimSemana(Double taxaFimSemana) {
        this.taxaFimSemana = taxaFimSemana;
    }

    @Override
    public String toString() {
        return "Estadia{" +
                "tipoCliente=" + tipoCliente +
                ", taxaDiaSemana=" + taxaDiaSemana +
                ", taxaFimSemana=" + taxaFimSemana +
                '}';
    }
}
