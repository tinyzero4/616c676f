package com.breedish.test.leetcode.tasks;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Atomic {

    static class VariableHandlesUnitTest {
        public int publicTestVariable = 1;
        private int privateTestVariable = 1;
        public int variableToSet = 1;
        public int variableToCompareAndSet = 1;
        public int variableToGetAndAdd = 0;
        public byte variableToBitwiseOr = 0;
    }

    static class Obj {

        static AtomicIntegerFieldUpdater<Obj> stateUpdater = AtomicIntegerFieldUpdater.newUpdater(Obj.class, "state");

        volatile int state;

        Obj(int state) {
            this.state = state;
        }

        public final int getState() {
            return this.state;
        }
    }


    public static void main(String[] args) throws Exception {
        var o = new Obj(2);
        Obj.stateUpdater.set(o, 33);
        System.out.println(o.state);

        VarHandle PUBLIC_TEST_VARIABLE = MethodHandles.privateLookupIn(VariableHandlesUnitTest.class, MethodHandles.lookup())
                .findVarHandle(VariableHandlesUnitTest.class, "publicTestVariable", int.class);

        System.out.println(PUBLIC_TEST_VARIABLE.coordinateTypes().size());
        System.out.println(PUBLIC_TEST_VARIABLE.coordinateTypes().get(0));
    }

}
