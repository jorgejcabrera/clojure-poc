(ns clojure-poc.exercise-16
  (:require [clojure.test :refer :all]))

(defn last-symbol [l]
  (if (empty? l)
    nil
    (if (symbol? (last l))
      (last l)
      (last-symbol (butlast l))
      )
    )
  )

(deftest testing-function
  (testing "la función last-symbol debería funcionar bien cuando la lista no contiene simbolos"
    (is (= (last-symbol '(1 2 3 4)) nil))
    )
  (testing "la función last-symbol debería funcionar bien cuando la lista está vacía"
    (is (= (last-symbol '()) nil))
    )
  (testing "la función last-symbol debería funcionar bien cuando la lista está vacía"
    (is (= (last-symbol '()) nil))
    )
  (testing "la función last-symbol debería funcionar bien"
    (def a (symbol "a"))
    (is (= (last-symbol '(1 2 a 3)) a))
    )
  (testing "la función symbol? debería funcionar bien"
    (is (= (symbol? (symbol "b")) true))
    )
  (testing "la función symbol debería funcionar bien"
    (def a (symbol "b"))
    (is (= (symbol? a) true))
    )
  (testing "la función butlast debería devolver todos los elementos menos el último"
    (is (= (butlast '(1 2 3 4)) '(1 2 3)))
    )
  )