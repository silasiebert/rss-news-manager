/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.core;

import br.udesc.argc.persistence.model.Feed;
import br.udesc.argc.persistence.model.Subject;
import java.util.List;

/**
 *
 * @author gabrielnaoto
 */
public interface SubjectDAO {
    
    public void insert(Subject object);

    public boolean delete(int id);
    
    public boolean update(Subject object);

    public Subject find(int id);

    public List<Subject> list();
    
}
