package it.prova.gestionesmartphoneapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "smartphone")
public class Smartphone {

	// (id, marca, modello, prezzo, versioneOS)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "prezzo")
	private double prezzo;
	@Column(name = "versioneos")
	private int versioneOS;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "smartphone_app", joinColumns =  @JoinColumn(name = "smartphone_id",referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name= "app_id", referencedColumnName = "ID"))
	List<App> apps = new ArrayList<>();

	public Smartphone() {
		super();
	}

	public Smartphone(String marca, String modello, double prezzo, int versioneOS) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.prezzo = prezzo;
		this.versioneOS = versioneOS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getVersioneOS() {
		return versioneOS;
	}

	public void setVersioneOS(int versioneOS) {
		this.versioneOS = versioneOS;
	}

	public List<App> getApps() {
		return apps;
	}

	public void setApps(List<App> apps) {
		this.apps = apps;
	}

}
