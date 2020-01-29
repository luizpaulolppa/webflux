package com.apirest.webflux.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "play_list")
public class PlayList {

	@Id
	private String id;
	private String nome;

	public PlayList(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public PlayList() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.nome;
	}

}