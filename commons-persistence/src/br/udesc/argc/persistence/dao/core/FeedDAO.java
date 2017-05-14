/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.core;

import br.udesc.argc.persistence.model.Feed;
import java.util.List;

/**
 *
 * @author gabrielnaoto
 */
public interface FeedDAO {
    
    public void insert(Feed object);

    public boolean delete(int id);
    
    public boolean update(Feed object);

    public Feed find(int id);

    public List<Feed> list();
    
}
