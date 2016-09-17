/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dirceu
 */
package br.unip.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.modelos.Cidade;

@Repository
public interface RepositorioCidade extends JpaRepository<Cidade,Long> {
	public Cidade findById(Long id);
	public String findNameById(Long id);
	public Cidade findByNameAndId(String name, Long id);

}

