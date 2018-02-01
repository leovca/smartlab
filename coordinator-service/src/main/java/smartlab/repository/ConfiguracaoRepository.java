package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.Configuracao;
import smartlab.model.Recomendacao;
import smartlab.model.UserPreference;

import java.util.List;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, String> {

    Configuracao findTopByOrderByIdDesc();

}