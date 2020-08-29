package info.loenwind.autosave.handlers.java.util;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NonnullType;
import info.loenwind.autosave.util.TypeUtil;

@SuppressWarnings("rawtypes")
public class HandleSimpleCollection<T extends Collection> extends HandleCollection<T> {
  
  private final @Nonnull Supplier<@NonnullType ? extends T> factory;

  public HandleSimpleCollection(Class<? extends T> clazz) {
    this(clazz, TypeUtil.defaultConstructorFactory(clazz));
  }

  public HandleSimpleCollection(Class<? extends T> clazz, Supplier<? extends T> factory) {
    super(clazz);
    this.factory = factory;
  }
  
  public HandleSimpleCollection(Class<? extends T> clazz, Supplier<? extends T> factory, Registry registry, Type... types) throws NoHandlerFoundException {
    super(clazz, registry, types);
    this.factory = factory;
  }
  
  @Override
  protected T makeCollection() {
    return factory.get();
  }

  @Override
  @Nullable
  protected IHandler<? extends T> create(Registry registry, Type... types) throws NoHandlerFoundException {
    return new HandleSimpleCollection<>(clazz, factory, registry, types);
  }

}
