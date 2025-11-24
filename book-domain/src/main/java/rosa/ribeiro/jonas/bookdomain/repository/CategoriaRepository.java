package rosa.ribeiro.jonas.bookdomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rosa.ribeiro.jonas.bookdomain.model.categoria.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}
