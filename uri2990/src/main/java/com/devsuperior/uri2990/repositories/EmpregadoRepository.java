package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true,value = "select e.cpf, e.enome, d.dnome  " +
            "from empregados e  " +
            "inner join departamentos d on e.dnumero = d.dnumero  " +
            "where e.cpf not in( " +
            "select empregados.cpf " +
            "from empregados " +
            "inner join trabalha t on t.cpf_emp =e.cpf) " +
            "order by e.cpf")
    List<EmpregadoDeptProjection> search1();
    @Query("select new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) " +
            "from Empregado obj  " +
            "where obj.cpf not in ( " +
            "select obj.cpf " +
            "from Empregado obj" +
            "inner join obj.projetosOndeTrabalha) " +
            "order by obj.cpf")
    List<EmpregadoDeptDTO> search2();


    @Query(nativeQuery = true,value = "select e.cpf, e.enome, d.dnome  " +
            "from empregados e  " +
            "inner join departamentos d on e.dnumero = d.dnumero  " +
            "left join trabalha on trabalha.cpf_emp = empregrados.cpf " +
            "wgere trabalha.cpf_emp is null " +
            "order by e.cpf")
    List<EmpregadoDeptProjection> search3();
}
