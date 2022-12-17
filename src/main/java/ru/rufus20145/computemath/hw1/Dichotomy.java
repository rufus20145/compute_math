package ru.rufus20145.computemath.hw1;

import java.util.List;

import ru.rufus20145.computemath.Computable;

public class Dichotomy {
    private Dichotomy() {
    }

    /**
     * Вычислительный процесс для метода дихотомии.
     * 
     * @param equation Уравнение.
     * @param segment  Сегмент, на котором будет производиться поиск корня.
     * @param accuracy Требуемая точность.
     * @return Количество итераций, потребовавшееся для достижения требуемой
     *         точности. Если точность не была достигнута, возвращается -1.
     */
    static int runDichotomy(Computable equation, DichSegment segment, double accuracy) {
    
        int numberOfIterations = 0;
    
        // Проверка наличия корня на сегменте
        if (equation.compute(segment.getLeft()) * equation.compute(segment.getRight()) > 0) {
            System.out.println("Wrong segment. Can`t find root.");
            return -1; // Если корня нет, то возвращаем -1
        }
    
        do {
            numberOfIterations++;
            double centerCoord = (segment.getLeft() + segment.getRight()) / 2;
            if (equation.compute(centerCoord) * equation.compute(segment.getLeft()) < 0) {
                segment.setRight(centerCoord);
            } else {
                segment.setLeft(centerCoord);
            }
            System.out.printf("Iteration: %2d. Current accuracy: %.8f.%n", numberOfIterations, segment.getLength());
        } while (Math.abs(segment.getRight() - segment.getLeft()) > accuracy);
    
        System.out.println(
                "RESULT: x = " + (Math.random() * (segment.getRight() - segment.getLeft()) + segment.getLeft()));
        return numberOfIterations;
    }

    /**
     * @param dichotomySegments Лист, в который добавляются сегменты для метода
     *                          дихотомии.
     */
    static void addDichSegments(List<DichSegment> dichotomySegments) {
        dichotomySegments.add(new DichSegment(0, 1));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(0, 1));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(0, 1));
    }

}
