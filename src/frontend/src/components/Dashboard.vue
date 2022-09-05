<template>
  <NavBar></NavBar>
  <img
    class="dashboard-background"
    src="../assets/images/dashboard-background.jpg"
    alt=""
  />
  <h2 class="welcome">Welcome back, Andjela</h2>
  <div style="margin-left: 100px">
    <h1>{{ msg }}</h1>
    <div class="grid">
      <div class="col-2"><EnergyRatio></EnergyRatio></div>
      <div class="col-5"><TargetsKnob></TargetsKnob></div>
      <div class="col-5"><CaloriesBurned></CaloriesBurned></div>
    </div>
    </div>
   <!--<div class="energy-ration">
    <EnergyRatio></EnergyRatio>
    <TargetsKnob></TargetsKnob>
    </div> 
    <div class="grid">
        <div class="col-12 md:col-6 p-fluid">
          <div class="card">
            <h5>InputText</h5>
            <div class="grid formgrid">
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <InputText type="text" placeholder="Default"></InputText>
              </div>
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <InputText
                  type="text"
                  placeholder="Disabled"
                  :disabled="true"
                ></InputText>
              </div>
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <InputText
                  type="text"
                  placeholder="Invalid"
                  class="p-invalid"
                />
              </div>
            </div>

            <Card>
            <h5>Icons</h5>
            <div class="grid formgrid">
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <span class="p-input-icon-left">
                  <i class="pi pi-user" />
                  <InputText type="text" placeholder="Username" />
                </span>
              </div>
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <span class="p-input-icon-right">
                  <InputText type="text" placeholder="Search" />
                  <i class="pi pi-search" />
                </span>
              </div>
              <div class="col-12 mb-2 lg:col-4 lg:mb-0">
                <span class="p-input-icon-left p-input-icon-right">
                  <i class="pi pi-user" />
                  <InputText type="text" placeholder="Search" />
                  <i class="pi pi-search" />
                </span>
              </div>
            </div>
</Card>
            <h5>Float Label</h5>
            <span class="p-float-label">
              <InputText id="username" type="text" v-model="floatValue" />
              <label for="username">Username</label>
            </span>

            <h5>Textarea</h5>
            <TextArea
              placeholder="Your Message"
              :autoResize="true"
              rows="3"
              cols="30"
            ></TextArea>

            <h5>AutoComplete</h5>
            <AutoComplete
              placeholder="Search"
              id="dd"
              :dropdown="true"
              :multiple="true"
              v-model="selectedAutoValue"
              :suggestions="autoFilteredValue"
              @complete="searchCountry($event)"
              field="name"
            />

            <h5>Calendar</h5>
            <MyCalendar
              :showIcon="true"
              :showButtonBar="true"
              v-model="calendarValue"
            ></MyCalendar>

            <h5>Spinner</h5>
            <InputNumber
              v-model="inputNumberValue"
              showButtons
              mode="decimal"
            ></InputNumber>

            <h5>Chips</h5>
            <Chips v-model="chipsValue" />
          </div>

          <MyCard>
            <h5>Slider</h5>
            <InputText v-model.number="sliderValue" />
            <Slider v-model="sliderValue" />

            <h5>Rating</h5>
            <Rating v-model="ratingValue" />

            <h5>Input Switch</h5>
            <InputSwitch v-model="switchValue" />
          </MyCard>
        </div>

        <div class="col-12 md:col-6">
          <div class="card">
            <h5>RadioButton</h5>
            <div class="grid">
              <div class="col-12 md:col-4">
                <div class="field-radiobutton">
                  <RadioButton
                    id="option1"
                    name="option"
                    value="Option 1"
                    v-model="radioValue"
                  />
                  <label for="option1">Option 1</label>
                </div>
              </div>
              <div class="col-12 md:col-4">
                <div class="field-radiobutton">
                  <RadioButton
                    id="option2"
                    name="option"
                    value="Option 2"
                    v-model="radioValue"
                  />
                  <label for="option2">Option 2</label>
                </div>
              </div>
              <div class="col-12 md:col-4">
                <div class="field-radiobutton">
                  <RadioButton
                    id="option3"
                    name="option"
                    value="Option 3"
                    v-model="radioValue"
                  />
                  <label for="option3">Option 3</label>
                </div>
              </div>
            </div>

            <h5>Checkbox</h5>
            <div class="grid">
              <div class="col-12 md:col-4">
                <div class="field-checkbox">
                  <Checkbox
                    id="checkOption1"
                    name="option"
                    value="Option 1"
                    v-model="checkboxValue"
                  />
                  <label for="checkOption1">Option 1</label>
                </div>
              </div>
              <div class="col-12 md:col-4">
                <div class="field-checkbox">
                  <Checkbox
                    id="checkOption2"
                    name="option"
                    value="Option 2"
                    v-model="checkboxValue"
                  />
                  <label for="checkOption2">Option 2</label>
                </div>
              </div>
              <div class="col-12 md:col-4">
                <div class="field-checkbox">
                  <Checkbox
                    id="checkOption3"
                    name="option"
                    value="Option 3"
                    v-model="checkboxValue"
                  />
                  <label for="checkOption3">Option 3</label>
                </div>
              </div>
            </div>
          </div>

          <div class="card p-fluid">
            <h5>Listbox</h5>
            <Listbox
              v-model="listboxValue"
              :options="listboxValues"
              optionLabel="name"
              :filter="true"
            />

            <h5>Dropdown</h5>
            <Dropdown
              v-model="dropdownValue"
              :options="dropdownValues"
              optionLabel="name"
              placeholder="Select"
            />

            <h5>MultiSelect</h5>
            <MultiSelect
              v-model="multiselectValue"
              :options="multiselectValues"
              optionLabel="name"
              placeholder="Select Countries"
              :filter="true"
            ></MultiSelect>
          </div>

          <div class="card p-fluid">
            <h5>ToggleButton</h5>
            <ToggleButton v-model="toggleValue" onLabel="Yes" offLabel="No" />

            <h5>SelectButton</h5>
            <SelectButton
              v-model="selectButtonValue1"
              :options="selectButtonValues1"
              optionLabel="name"
            />

            <h5>SelectButton - Multiple</h5>
            <SelectButton
              v-model="selectButtonValue2"
              :options="selectButtonValues2"
              optionLabel="name"
              :multiple="true"
            />
          </div>
        </div>
      </div>
    </div>-->
</template>

<script>
import NavBar from "./NavBar.vue";
import EnergyRatio from "./EnergyRatio.vue";
import TargetsKnob from "./TargetsKnob.vue";
import CaloriesBurned from "./CaloriesBurned.vue";
export default {
  name: "Dashboard",
  components: { NavBar, EnergyRatio, TargetsKnob, CaloriesBurned },
  data() {
    return {
      msg: "",
    };
  },
  mounted() {
    fetch("/api/calculator/hello")
      .then((response) => response.text())
      .then((data) => {
        this.msg = data;
      });
  },
};
</script>

<style scoped>
.dashboard-background {
  height: 200px;
  width: 100%;
  margin-top: -10px;
  object-fit: cover;
}

.welcome {
  margin-top: -65px;
  margin-left: 90px;
  color: white;
  text-shadow: 2px 2px 10px black;
  font-size: 35px;
}

.energy-ration {
  display: flex;
}
</style>