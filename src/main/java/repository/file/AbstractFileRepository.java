package repository.file;

import domain.Entity;
import repository.memory.InMemoryRepository;
import validators.Validator;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;

    public AbstractFileRepository(String fileName, Validator<E> validator) {
        super(validator);

        this.fileName = fileName;
        loadData();
    }

    private void loadData()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String newLine;
            while((newLine=reader.readLine())!=null){
                List<String> data = Arrays.asList(newLine.split(";"));
                E entity=extractEntity(data);
                super.save(entity);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public abstract E extractEntity(List<String> attributes);

    protected abstract String createEntityAsString(E entity);

    @Override
    public E save(E entity) {
        super.save(entity);
        writeToFile();
        return entity;
    }

    @Override
    public void delete(E entity) {
        super.delete(entity);
        writeToFile();
    }

    protected void writeToFile(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(fileName))){

            for (E entity : findAll()) {
                writer.write(createEntityAsString(entity));
                writer.newLine();
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
