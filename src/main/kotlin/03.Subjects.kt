import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

fun main() {

    // Subjects
    // - Observable 과 Observer 의 성격을 둘 다 가지고 있음
    // - 즉 Subscribe 를 달 수 있으며 동시에 onNext, onComplete 등을 달 수 있음

    // 1. PublishSubject
    // - 구독한 시점부터 새로운 데이터를 가져오는 Subject

    val xSubject = PublishSubject.create<Int>()
    xSubject.subscribe { println("첫번째 $it") }
    xSubject.onNext(1)
    Thread.sleep(1000L)
    xSubject.subscribe { println("----두번째 $it") }
    xSubject.onNext(2)
    xSubject.onNext(3)
    Thread.sleep(1000L)
    xSubject.subscribe { println("********세번째 $it") }
    xSubject.onNext(4)
    xSubject.onComplete()
    println()


    // 2. BehaviorSubject
    // - 구독한 시점 직전 데이터부터 가져오는 Subject

    val xSubject2 = BehaviorSubject.create<Int>()
    xSubject2.subscribe { println("첫번째 $it") }
    xSubject2.onNext(1)
    Thread.sleep(1000L)
    xSubject2.subscribe { println("----두번째 $it") }
    xSubject2.onNext(2)
    xSubject2.onNext(3)
    Thread.sleep(1000L)
    xSubject2.subscribe { println("********세번째 $it") }
    xSubject2.onNext(4)
    xSubject2.onComplete()
    println()


    // 3. ReplaySubject
    // - 지금까지 발행된 데이터 모두를 가져오는 Subject

    val xSubject3 = ReplaySubject.create<Int>()
    xSubject3.subscribe { println("첫번째 $it") }
    xSubject3.onNext(1)
    Thread.sleep(1000L)
    xSubject3.subscribe { println("----두번째 $it") }
    xSubject3.onNext(2)
    xSubject3.onNext(3)
    Thread.sleep(1000L)
    xSubject3.subscribe { println("********세번째 $it") }
    xSubject3.onNext(4)
    xSubject3.onComplete()
    println()


    // 4. AsyncSubject
    // - Complete 되었을 때 가장 마지막 데이터를 받는 Subject

    val xSubject4 = AsyncSubject.create<Int>()
    xSubject4.subscribe { println("첫번째 $it") }
    xSubject4.onNext(1)
    Thread.sleep(1000L)
    xSubject4.subscribe { println("----두번째 $it") }
    xSubject4.onNext(2)
    xSubject4.onNext(3)
    Thread.sleep(1000L)
    xSubject4.subscribe { println("********세번째 $it") }
    xSubject4.onNext(4)
    xSubject4.onComplete()
    println()

}