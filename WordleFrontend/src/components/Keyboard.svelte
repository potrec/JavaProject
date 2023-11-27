<script>
  import CONSTANTS from "../constants";
  // $: bgClass = (letter) => {
  //       return CONSTANTS.LETTER_STATUS_TO_BG_MAP[$letterStatuses[letter.toUpperCase()]];
  //   }
    // $: bgClass = (letter) => {
    //     return CONSTANTS.LETTER_STATUS_TO_BG_MAP[LETTER_STATES.CORRECT_SPOT];
    // }
</script>
<div>
  {#each CONSTANTS.KEYBOARD_ROWS_ARR as row }
  <div class="flex gap-2 justify-center mb-2">
      {#each row as letter }
          <button class={`p-2 h-10 rounded-md flex items-center justify-center border-2 border-gray-100 bg-transparent`}>{letter}</button>
      {/each}
      </div>
  {/each}
</div>