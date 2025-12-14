import pandas as pd
import numpy as np

def clean_population_data(df):
    population_data = []
    for index, row in df.iterrows():
        if pd.notna(row[0]) and str(row[0]).isdigit():
            year = int(row[0])
            heck_cattle = clean_number(row[1])
            konik_horses = clean_number(row[2])
            red_deer = clean_number(row[3])
            total = clean_number(row[4])

            population_data.append({
                    'Year': year,
                    'Heck_Cattle': heck_cattle,
                    'Konik_Horses': konik_horses,
                    'Red_Deer': red_deer,
                    'Total': total
                })
    population_df = pd.DataFrame(population_data)
    return population_df

def clean_births_data(df):
    biths_data = []
    for index, row in df.iterrows():
        if pd.notna(row[12]) and str(row[12]).isdigit():
            year = int(row[12])
            heck_cattle = clean_number(row[13])
            konik_horses = clean_number(row[14])
            red_deer = clean_number(row[15])
            total = clean_number(row[16])

            biths_data.append({
                    'Year': year,
                    'Heck_Cattle': heck_cattle,
                    'Konik_Horses': konik_horses,
                    'Red_Deer': red_deer,
                    'Total': total
                })
    population_df = pd.DataFrame(biths_data)
    return population_df

def clean_deaths_data(df):
    deaths_data = []
    for index, row in df.iterrows():
        if pd.notna(row[6]) and str(row[6]).isdigit():
            year = int(row[6])
            heck_cattle = clean_number(row[7])
            konik_horses = clean_number(row[8])
            red_deer = clean_number(row[9])
            total = clean_number(row[10])

            deaths_data.append({
                    'Year': year,
                    'Heck_Cattle': heck_cattle,
                    'Konik_Horses': konik_horses,
                    'Red_Deer': red_deer,
                    'Total': total
                })
    population_df = pd.DataFrame(deaths_data)
    return population_df


def clean_number(value):
    if pd.isna(value):
        return np.nan
    str_value = str(value).strip()
    if str_value.startswith('='):
        return np.nan
    if str_value and str_value.replace('.', '').replace('-', '').isdigit():
        try:
            return float(str_value)
        except ValueError:
            return np.nan
        
    return np.nan
    
def clean_oostvaardersplassen_data(file_path):
    
    
    # Read the Excel file
    df = pd.read_excel(file_path, sheet_name='2025', header=None)
    
    # Create clean tables for each dataset
    population_df = clean_population_data(df)
    births_df = clean_births_data(df)
    deaths_df = clean_deaths_data(df)
    
    return population_df, births_df, deaths_df
    
def save_clean_data(population_df, births_df, deaths_df, output_path):
    """Save the cleaned data to an Excel file with multiple sheets"""
    with pd.ExcelWriter(output_path, engine='openpyxl') as writer:
        population_df.to_excel(writer, sheet_name='Population_Dynamics', index=False)
        births_df.to_excel(writer, sheet_name='Birth_Rates', index=False)
        deaths_df.to_excel(writer, sheet_name='Death_Rates', index=False)


if __name__ == "__main__":
    # File paths
    input_file = "population.xlsx"
    output_file = "Clean_Data.xlsx"

    population_df, births_df, deaths_df = clean_oostvaardersplassen_data(input_file)

    save_clean_data(population_df, births_df, deaths_df, output_file)
