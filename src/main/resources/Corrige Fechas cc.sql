/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Diego
 * Created: 13/02/2019
 */

UPDATE cuenta_paciente c
       INNER JOIN plan_tratamiento p
               ON c.plan_tratamiento_id = p.id
SET    c.fecha_movimiento = p.fechacreacion  

//
UPDATE trabajos_tratamiento t
       INNER JOIN plan_tratamiento p
               ON t.plan_tratamiento_id = p.id
SET    t.trabajoTratamientoEnum = 'D' WHERE p.situacionPlanTratamientoEnum != 'PRESUPUESTO';

UPDATE trabajos_tratamiento t
       INNER JOIN plan_tratamiento p
               ON t.plan_tratamiento_id = p.id
SET    t.trabajoTratamientoEnum = 'P' WHERE p.situacionPlanTratamientoEnum = 'PRESUPUESTO';