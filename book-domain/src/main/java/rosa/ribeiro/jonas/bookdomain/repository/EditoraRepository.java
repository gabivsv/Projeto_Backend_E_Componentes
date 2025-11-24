package rosa.ribeiro.jonas.bookdomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rosa.ribeiro.jonas.bookdomain.model.editora.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, String> {
}
