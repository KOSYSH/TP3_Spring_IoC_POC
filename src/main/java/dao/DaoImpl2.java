package dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("dao2")
@Profile({"dev", "default"})
public class DaoImpl2 implements IDao {
  @Override public double getValue(){ return 150.0; }
}
