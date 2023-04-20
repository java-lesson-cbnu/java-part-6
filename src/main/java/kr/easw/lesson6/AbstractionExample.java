package kr.easw.lesson6;

import java.util.HashMap;
import java.util.Map;

/**
 * 해당 문제는 Bank 클래스를 상속해, withdraw와 deposit을 구현하여야 합니다.
 *
 * 해당 문제는 다음 조건을 충족해야 합니다:
 * - Bank를 상속받는 클래스를 최소 1개 구현해야 합니다.
 * - {@link AbstractionExample#getBank()} 상속받은 클래스가 반환되도록 하여야 합니다.
 * - 상속받은 메서드에서 Exception이 발생하면 안됩니다.
 */
public class AbstractionExample {

    public static void main(String[] args) {
        getBank().withdraw("Test1", 50);
        getBank().deposit("Test1", 50);
    }

    public static Bank getBank() {
        throw new IllegalStateException("이곳에서 코드를 구현하십시오.");
    }

    abstract class Bank {
        private Map<String, Integer> map = new HashMap<>();

        public int get(String name) {
            return map.getOrDefault(name, 0);
        }

        public void set(String name, int amount) {
            map.put(name, amount);
        }

        public void withdraw(String name, int amount) {
            throw new IllegalStateException("지원하지 않는 기능입니다.");
        }

        public void deposit(String name, int amount) {
            throw new IllegalStateException("지원하지 않는 기능입니다.");
        }
    }
}
