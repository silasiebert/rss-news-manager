/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.core;

import br.udesc.argc.persistence.model.Feed;
import br.udesc.argc.persistence.model.News;
import java.util.List;

/**
 *
 * @author gabrielnaoto
 */
public interface NewsDAO {

    public void insert(News object);

    public List<News> list();

}
