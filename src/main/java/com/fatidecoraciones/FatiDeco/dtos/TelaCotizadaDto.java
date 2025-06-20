package com.fatidecoraciones.FatiDeco.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelaCotizadaDto {
    private double precioTela;
    private float cantTela;
    private double costoTela;
    private double precioConf;
    private int confec;
    private double costoConf;
    private double precioAcc;
    private double total;

    public TelaCotizadaDto() {
    }

    public TelaCotizadaDto(double precioTela, float cantTela, double costoTela, double precioConf, int confec, double costoConf, double precioAcc, double total) {
        this.precioTela = precioTela;
        this.cantTela = cantTela;
        this.costoTela = costoTela;
        this.precioConf = precioConf;
        this.confec = confec;
        this.costoConf = costoConf;
        this.precioAcc = precioAcc;
        this.total = total;
    }
}
