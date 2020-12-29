import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException


fun main() {

    // 생산자는 데이터를 생산해서 전달하는 역할을 함
    // 소비자를 등록하는 방법 두 가지

    // 1. Observer 방식
    // Observer 인터페이스를 구현한 객체를 Subscribe 해서 Consumer 를 추가
    // subscribe 의 반환 타입은 Unit 임

    val observer = object : Observer<Int> {
        override fun onComplete() {
            // Observable 이 완료된 경우
        }

        override fun onSubscribe(d: Disposable) {
            // Observable 이 데이터 전달할 준비가 되었을 때.
            // 작업 취소를 위한 Disposable 에 대한 레퍼런스를 여기서 받음
        }

        override fun onNext(t: Int) {
            // Observable 이 데이터를 전달할 때 호출
        }

        override fun onError(e: Throwable) {
            // Observable 이 에러를 전달할 때 호출. Error 시 Complete 없이 종료
        }
    }

    Observable.just(1, 2, 3, 4).subscribe(observer)


    // 2. Consumer 방식
    // 각각의 Consumer 를 subscribe 해서 소비자를 추가 (주로 이것을 사용한다고 함)
    // Consumer 는 메소드 한 개짜리 자바 인터페이스이므로 SAM 을 통해 람다로 표현 가능
    // subscribe 의 반환 타입이 Disposable 임

    val disposable: Disposable = Observable.just(1, 2, 3, 4)
        .subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") }
        )

    println()


    // 여러가지 생산자

    // 1. Observable
    // - 0개 에서 N 개의 데이터를 전달하는 생산자
    // - 기본적인 생산자로 단 건(0 or 1)이 아니면 대부분 Observable 을 사용
    // - Observer 방식으로 Consumer 등록  Observer 를 구현하여 전달
    // - Consumer 방식을 사용할 시 onNext, onComplete, onError 와 onSubscribe 가 있음

    val observer2 = Observable.just(11, 12, 13)
        .map {
            if (it == 12) throw IllegalStateException() // 12 에 에러 발생
            else it
        }
    observer2.subscribe(
        { println("onNext $it") },
        { println("onError") },
        { println("onComplete") },
        { println("onSubscribe") }
    )


    // 2. Single
    // - 오직 1개의 데이터를 전달하는 생산자
    // - HTTP GET Request 와 같이 결과가 1개의 데이터 or 실패인 경우 사용
    // - Observer 방식으로 Consumer 등록 시 SingleObserver 를 구현하여 전달
    // - Consumer 방식을 사용할 시 onSuccess 와 onError 만 있음

    Single.just(1)
        .subscribe(
            { println("onSuccess $it") },
            { println("onError") }
        )


    // 3. Completable
    // - 0개의 데이터를 전달하는 생산자
    // - DB 에 INSERT, UPDATE 와 같이 데이터가 필요 없이 성공 or 실패인 경우 사용
    // - Observer 방식으로 Consumer 등록 시시 CompletableObserver 를 구현하여 전달
    // - Consumer 방식을 사용할 시 onComplete 와 onError 만 있음

    Completable.complete()
        .subscribe(
            { println("onComplete") },
            { println("onError") }
        )


    // 4. Maybe
    // - 0개 또는 1개 데이터를 전달하는 생산자
    // - 예, 아니오 선택과 같이 (둘 중 하나 + 예외 경우) 에 쓸 수 있음
    // - Observer 방식으로 Consumer 등록 시 MaybeObserver 를 구현하여 전달
    // - Consumer 방식을 사용할 시 onSuccess, onComplete, onError 가 있음

    Maybe.empty<Unit>()
        .subscribe(
            { println("onSuccess $it") },
            { println("onComplete") },
            { println("onError") }
        )


    // 5. Flowable
    // - 데이터의 발행 속도가 구독자의 처리 속도보다 크게 빠를 때 사용 (BackPressure Issue)
    // - Observer 방식으로 Consumer 등록 시 FlowableSubscriber 를 구현하여 전달
    // - BackPressure Issue 를 처리하는 방법을 설정할 수 있음
    // - LiveDataReactiveStreams 를 사용하여 AAC LiveData 연계 가능

    Flowable.just(1, 2, 3, 4)
        .subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") }
        )

}

