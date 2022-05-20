package tads.eaj.br.produtosmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.br.produtosmvc.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findProdutoByDescricao(String descricao);
}
