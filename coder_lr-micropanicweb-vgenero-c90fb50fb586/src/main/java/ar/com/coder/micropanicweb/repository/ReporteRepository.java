/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Evento;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lgaray
 */
@Transactional
@Repository("reporteRepository")
public interface ReporteRepository extends JpaRepository<Evento, Long> {

    @Query(value = "SELECT SUM(case when id_evento=0 then 0 else 1 END) AS id_evento,mes,tipo_evento \n" +
"     from ( select orden,id_evento as id_evento,MONTHNAME(fecha_operacion) as mes,denominacion tipo_evento \n" +
"from eventos e inner \n" +
"JOIN meses on mes_ingles=MONTHNAME(fecha_operacion) \n" +
"INNER JOIN param_tipos_eventos te ON e.id_tipo_evento=te.id_tipo group by mes,tipo_evento,orden\n" +
"UNION select orden,'0' as id_evento ,mes_ingles, '' as tipo_evento from meses m ) t\n" +
" GROUP by mes,tipo_evento \n" +
"ORDER by orden "
            , nativeQuery = true)
    //WHERE fecha_operacion >= ?1 and fecha_operacion <=?2\n
    public List<String> findReportEventobyFechaTipo( );
}
