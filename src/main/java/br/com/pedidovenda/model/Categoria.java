package br.com.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	private Categoria categoriaPai;
	private List<Categoria> subcategorias = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, length = 60)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToOne
	@JoinColumn(name = "categoria_pai_id")
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}
	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
	@OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL)
	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Categoria other = (Categoria) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
