package com.debin.challengegan.characters.interactors

import com.challengegan.characters.domain.executor.PostExecutionThread
import com.challengegan.characters.domain.executor.ThreadExecutor
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.domain.repository.ICharactersRepository
import com.debin.challengegan.characters.domain.utils.OpenForTesting
import io.reactivex.Single

@OpenForTesting
open class GetCharacters(private val characterRepository: ICharactersRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<CharacterResponseItem>, Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(
        params: Int?,
        arg2: String?
    ): Single<List<CharacterResponseItem>> {
        return characterRepository.getCharacters()
    }
}