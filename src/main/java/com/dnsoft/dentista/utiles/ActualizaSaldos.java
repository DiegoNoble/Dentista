/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.utiles;


import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.CuentaPaciente;
import java.math.BigDecimal;
import java.util.List;

public class ActualizaSaldos {

    List<CuentaPaciente> ccToReturnPropietario;
    List<Caja> cajaToReturn;

    public List<CuentaPaciente> ActualizaSaldosPacientes(List<CuentaPaciente> CuentaCorrientePaciente) {

        BigDecimal saldo = BigDecimal.ZERO;
        this.ccToReturnPropietario = CuentaCorrientePaciente;

        for (CuentaPaciente movimiento : CuentaCorrientePaciente) {
            BigDecimal credito = movimiento.getHaber();

            BigDecimal debito = movimiento.getDebe();

            saldo = credito.subtract(debito).add(saldo);

            movimiento.setSaldo(saldo);
        }
        return ccToReturnPropietario;
    }

public List<Caja> ActualizaSaldosCaja(List<Caja> movimientosCaja) {

        BigDecimal saldo = BigDecimal.ZERO;
        this.cajaToReturn = movimientosCaja;

        for (Caja movimiento : movimientosCaja) {
            BigDecimal credito = movimiento.getSalida();

            BigDecimal debito = movimiento.getEntrada();

            saldo = credito.subtract(debito).add(saldo);

            //movimiento.setSaldo(saldo);
        }
        return cajaToReturn;
    }
  

 
}
