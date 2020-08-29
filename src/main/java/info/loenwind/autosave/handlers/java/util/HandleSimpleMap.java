package info.loenwind.autosave.handlers.java.util;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NonnullType;
import info.loenwind.autosave.util.TypeUtil;

@SuppressWarnings("rawtypes")
public class HandleSimpleMap<T extends Map> extends HandleMap<T> {
  
  private final @Nonnull Supplier<@NonnullType ? extends T> factory;

  public HandleSimpleMap(Class<? extends T> clazz) {
    this(clazz, TypeUtil.defaultConstructorFactory(clazz));
  }

  public HandleSimpleMap(Class<? extends T> clazz, Supplier<? extends T> factory) {
    super(clazz);
    this.factory = factory;
  }
  
  public HandleSimpleMap(Class<? extends T> clazz, Supplier<? extends T> factory, Registry registry, Type... types) throws NoHandlerFoundException {
    super(clazz, registry, types);
    this.factory = factory;
  }

  @Override
  protected T createMap() {
    return factory.get();
  }

  @Override
  protected IHandler<? extends T> create(Registry registry, Type... types) throws NoHandlerFoundException {
    return new HandleSimpleMap<>(clazz, factory, registry, types);
  }

}
