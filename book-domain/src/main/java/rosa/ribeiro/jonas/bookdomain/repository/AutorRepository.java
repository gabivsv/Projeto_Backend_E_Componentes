package rosa.ribeiro.jonas.bookdomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rosa.ribeiro.jonas.bookdomain.model.autor.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {
}
