import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(){
    // 필터링 연산자

    // 1. debounce
    // - 일정 시간 다른 아이템이 생성되지 않으면 데이터를 전달하는 연산자
    // - ex) 사용자 키보드 입력대로 자동 API 호출 등

    Observable.interval(250, TimeUnit.MILLISECONDS)
        .debounce(333, TimeUnit.MILLISECONDS)
        .subscribe(::println)
    Thread.sleep(1000)


    // 2. distinct / distinctUntilChange
    // - distinct : 중복되지 않는 데이터만 전달
    // - distinctUntilChange : 연속하여 같은 값은 전달하지 않음

    data class T(val a: Int, val b: Int)
    Observable.just(T(2, 3), T(2, 4), T(1, 5), T(7, -1), T(2, 2))
        .distinct { it.a + it.b }  // Key Selector : 예제에서는 두 수의 합이 이전에 나왔던 합이면 거름
        .subscribe(::println)


    // 3. filter / ofType
    // - filter : 특정 조건에 맞는 데이터만 전달
    // - ofType : 특정 타입에 맞는 데이터만 전달. 전달 시 Type Casting 이 되어있음

    Observable.just(11, true, "Hello", "Rx", false)
        .ofType(String::class.java)
        .filter{ it.length == 2}
        .subscribe(::println)


    // 4. ignoreElements
    // -  모든 데이터를 무시하고 Completable 을 리턴 (데이터 받긴 하지만 성공여부만 알고 싶음)

    Observable.just(6, 4, 2)
        .ignoreElements()
        .subscribe(::println)


    // 5. throttleFirst / throttleLast
    // - 특정 시간동안 들어오는 데이터 중 첫번째 / 마지막 데이터만 전달

    Observable.interval(0, 100, TimeUnit.MILLISECONDS)
        .throttleLast(250, TimeUnit.MILLISECONDS)
        .subscribe {
            println(System.currentTimeMillis())
            println(it)
        }
    Thread.sleep(1000)


    // 6. skip/take
    // - skip : 앞부터 n개의 데이터를 생략하고 전달
    // - take : 앞부터 n개의 데이터만 전달
    // - last, while, until 등 응용할 수 있음

    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .take(9)
        .skip(2)
        .skipLast(2)
        .takeLast(3)
        .subscribe(::println)
}