package dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoFile")
@Profile({"file", "default"})
public class DaoFile implements IDao {
  @Override public double getValue(){ return 180.0; }
}
