
package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contato {
	@Id @GeneratedValue Long id;
	private String nome;
	private String telefone;
	
	Contato(){}
	
	Contato(String nome, String telefone){
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Contato))
	      return false;
	    Contato Contato = (Contato) o;
	    return Objects.equals(this.id, Contato.id) && Objects.equals(this.nome, Contato.nome)
	        && Objects.equals(this.telefone, Contato.telefone);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.nome, this.telefone);
	  }

	  @Override
	  public String toString() {
	    return "Contato{" + "id=" + this.id + ", nome='" + this.nome + '\'' + ", telefone='" + this.telefone + '\'' + '}';
	  }
	

}
