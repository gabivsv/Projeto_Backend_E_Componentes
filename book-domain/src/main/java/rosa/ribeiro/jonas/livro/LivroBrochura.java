package rosa.ribeiro.jonas.livro;

import rosa.ribeiro.jonas.autor.Autor;
import rosa.ribeiro.jonas.editora.Editora;
import rosa.ribeiro.jonas.categoria.Categoria;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("BROCHURA")
public class LivroBrochura extends Livro{
    public LivroBrochura(){}

    public LivroBrochura(String titulo, String isbn, int numPaginas, int anoPublicacao, String resumo, int quantidadeEstoque, BigDecimal precoBase, Editora editora, List<Autor> autores, Set<Categoria> categorias) {
        super(titulo, isbn, numPaginas, anoPublicacao, resumo, quantidadeEstoque, precoBase, editora, autores, categorias);
    }

    @Override
    public BigDecimal calcularPreco() {
        return (getPrecoBase().multiply(new BigDecimal("0.95")));
    }
}
