(ns clojure-poc.exercise-17
  (:require [clojure.test :refer :all]))

(defn mid
  [l]
  (if (empty? l)
    nil
    (let [size (count l) isPair? (even? (count l))]
      (if isPair?
        (nth l (/ size 2))
        (nth l (int (/ size 2)))
        )
      )
    )
  )

(deftest testing-function

  (testing "la función mid debe darme el elemento en la mitad cuando su longitud es par"
    (is (= (mid '()) nil))
    )
  (testing "la función mid debe darme el elemento en la mitad cuando su longitud es par"
    (is (= (mid '(8 32 2 6)) '2))
    )
  (testing "la función mid debe darme el elemento en la mitad"
    (is (= (mid '(1 2 9 4 5)) '9))
    )
  (testing "la función nth debe darme el n-ésimo elemento"
    (is (= (nth '(1 2 3 4 5) '0) '1))
    )
  (testing "la función nth debe darme el n-ésimo elemento"
    (is (= (nth '(1 2 3 4 5) '0) '1))
    )
  (testing "la función count debe funcionar bien"
    (is (= (count '(1 2 3 4 5)) 5))
    )
  )