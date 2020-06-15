package nossagrana.emprestimo.dto;

import nossagrana.emprestimo.entity.Emprestimo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class EmprestimoDTO {
    public double getMontante() {
        return montante;
    }

    public void setMontante(double montante) {
        this.montante = montante;
    }

    public ZonedDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(ZonedDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public ZonedDateTime getDataBase() {
        return dataBase;
    }

    public void setDataBase(ZonedDateTime dataBase) {
        this.dataBase = dataBase;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaCET() {
        return taxaCET;
    }

    public void setTaxaCET(double taxaCET) {
        this.taxaCET = taxaCET;
    }

    private double montante;
    private ZonedDateTime dataVencimento;
    private ZonedDateTime dataBase;
    private double taxaJuros;
    private double taxaCET;

    public EmprestimoDTO(double montante, ZonedDateTime dataVencimento, ZonedDateTime dataBase, double taxaJuros, double taxaCET) {
        this.montante = montante;
        this.dataVencimento = dataVencimento;
        this.dataBase = dataBase;
        this.taxaJuros = taxaJuros;
        this.taxaCET = taxaCET;
    }

    public EmprestimoDTO(Emprestimo entity) {
        this.dataBase = convertToZonedDateTime(entity.getDataBase());
        this.dataVencimento = convertToZonedDateTime(entity.getDataVencimento());
        this.montante = entity.getMontante();
        this.taxaCET = entity.getTaxaCET();
        this.taxaJuros = entity.getTaxaJuros();
    }

    public EmprestimoDTO() {
    }

//    public double getMontante() {
//        return montante;
//    }
//
//    public void setMontante(double montante) {
//        this.montante = montante;
//    }

//    public ZonedDateTime getDataVencimento() {
//        return dataVencimento;
//    }
//
//    public void setDataVencimento(ZonedDateTime dataVencimento) {
//        this.dataVencimento = dataVencimento;
//    }
//
//    public ZonedDateTime getDataBase() {
//        return dataBase;
//    }
//
//    public void setDataBase(ZonedDateTime dataBase) {
//        this.dataBase = dataBase;
//    }
//
//    public double getTaxaJuros() {
//        return taxaJuros;
//    }
//
//    public void setTaxaJuros(double taxaJuros) {
//        this.taxaJuros = taxaJuros;
//    }
//
//    public double getTaxaCET() {
//        return taxaCET;
//    }
//
//    public void setTaxaCET(double taxaCET) {
//        this.taxaCET = taxaCET;
//    }

    private ZonedDateTime convertToZonedDateTime(LocalDate data) {
        if(data != null) {
            return data.atStartOfDay(ZoneId.systemDefault());
        }
        else return null;
    }
}
