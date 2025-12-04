import pandas as pd
import numpy as np

df_population = pd.read_excel("Clean_Data.xlsx", sheet_name="Population_Dynamics")
df_births = pd.read_excel("Clean_Data.xlsx", sheet_name="Birth_Rates")
df_deaths = pd.read_excel("Clean_Data.xlsx" , sheet_name="Death_Rates")

def clean_data(df):
    df_clean = df.copy()
    # Interpolate only numeric columns, then round to nearest int
    numeric_cols = df_clean.select_dtypes(include=[np.number]).columns
    if len(numeric_cols) > 0:
        df_clean[numeric_cols] = df_clean[numeric_cols].interpolate()
        # Fill remaining NaNs with 0 before casting
        df_clean[numeric_cols] = df_clean[numeric_cols].fillna(0)
        # Round and cast to integer type
        df_clean[numeric_cols] = df_clean[numeric_cols].round().astype('Int64')
    # For non-numeric columns, still fill NaNs with empty string or keep as is
    non_numeric_cols = df_clean.columns.difference(numeric_cols)
    if len(non_numeric_cols) > 0:
        df_clean[non_numeric_cols] = df_clean[non_numeric_cols].fillna("")
    return df_clean

df_population_clean = clean_data(df_population)
df_births_clean = clean_data(df_births)
df_deaths_clean = clean_data(df_deaths)


with pd.ExcelWriter("Combined_data.xlsx") as writer:
    df_population_clean.to_excel(writer,sheet_name = "Population_Dynamics", index = False)
    df_births_clean.to_excel(writer,sheet_name = "Birth_Rates", index = False)
    df_deaths_clean.to_excel(writer,sheet_name = "Death_Rates", index = False)

