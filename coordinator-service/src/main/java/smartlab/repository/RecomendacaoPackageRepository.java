package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.Recomendacao;
import smartlab.model.RecomendacaoPackage;
import smartlab.model.UserPreference;

import java.util.List;

public interface RecomendacaoPackageRepository extends JpaRepository<RecomendacaoPackage, String> {


    RecomendacaoPackage findTopByOrderByIdDesc();

}