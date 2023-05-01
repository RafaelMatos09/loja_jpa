package com.alura.testes;

import com.alura.dao.CategoriaDao;
import com.alura.model.Categoria;
import com.alura.model.Produto;
import com.alura.dao.ProdutoDao;
import com.alura.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
       cadastrarProduto();
       EntityManager em = JpaUtil.getEntityManager();
       ProdutoDao produtoDao = new ProdutoDao(em);

       Produto p = produtoDao.buscarPorId(1l);
       System.out.println(p.getPreco());

       List<Produto> todos = produtoDao.buscarTodos();
       todos.forEach(p2 -> System.out.println(p.getNome()));

       BigDecimal precoDoProduto = produtoDao.buscarPecoDoProdutoComNome("Xiaomi Redmi");
       System.out.println("Preco do produto" + precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
         Produto celular = new Produto("Xiaomi Redmi","Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("XPO");
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        /*em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("1234");
        em.flush();
        em.clear();
        em.remove(celulares);
        em.flush();*/
    }
}
